package org.seamless_if.services.scheduler;

import java.util.Collection;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.seamless_if.processing.scheduler.JobState;
import org.seamless_if.processing.scheduler.WorkerState;
import org.seamless_if.processing.scheduler.dto.JobTO;
import org.seamless_if.processing.scheduler.dto.ModelChainInfoTO;
import org.seamless_if.processing.scheduler.dto.WorkerTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Unit tests for the SchedulerService, using Spring locally.
 *
 * @author Rob Knapen; Alterra, Wageningen UR, NL
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:WebContent/WEB-INF/spring-seamfaces-config-test.xml"})
public class SchedulerServiceTest {

    @Autowired
    @Qualifier("schedulerDao")
    private SchedulerService scheduler;

    private ModelChainInfoTO chainA;
    private ModelChainInfoTO chainB;
    private JobTO jobA;
    private String jobIdA;
    private JobTO jobB;
    //private String jobIdB;
    private WorkerTO workerA;
    private String workerIdA;
    private WorkerTO workerB;
    private String workerIdB;


    @Before
    public void setUp() throws Exception {
        // create some model chain info to use
        chainA = new ModelChainInfoTO();
        chainB = new ModelChainInfoTO();

        // make sure the scheduler is 'clean'
        scheduler.clear();

        // register worker A with scheduler, runs model chain A and B
        workerA = new WorkerTO();
        workerA.addAvailableModelChain(chainA);
        workerA.addAvailableModelChain(chainB);
        workerIdA = workerA.getId();
        scheduler.registerWorker(workerA);

        // register worker B with scheduler, only runs model chain B
        workerB = new WorkerTO();
        workerB.addAvailableModelChain(chainB);
        workerIdB = workerB.getId();
        scheduler.registerWorker(workerB);

        // add job A to scheduler, needs model chain A
        jobA = new JobTO();
        jobA.setModelChain(chainA);
        jobIdA = jobA.getId();
        scheduler.addJob(jobA);

        // add job B to scheduler, needs model chain B
        jobB = new JobTO();
        jobB.setModelChain(chainB);
        //jobIdB = jobB.getId();
        scheduler.addJob(jobB);
    }


    @Test
    public void jobManagementTest() {
        // get initial jobs list, must contain job A and B
        List<JobTO> jobs = scheduler.allJobs();
        Assert.assertNotNull(jobs);
        Assert.assertEquals(2, jobs.size());
        Assert.assertEquals(jobA, jobs.get(0));
        Assert.assertEquals(jobB, jobs.get(1));
        Assert.assertNotSame(jobs.get(0), jobs.get(1));
        printJobs(jobs);

        // add a new job
        JobTO newJob = new JobTO();
        scheduler.addJob(newJob);

        // job must now be in the list
        jobs = scheduler.allJobs();
        Assert.assertNotNull(jobs);
        Assert.assertEquals(3, jobs.size());
        Assert.assertEquals(newJob, jobs.get(2));
        Assert.assertNotSame(jobs.get(0), jobs.get(2));
        Assert.assertNotSame(jobs.get(1), jobs.get(2));
        printJobs(jobs);
    }


    @Test
    public void workerManagementTest() {
        // get initial workers list, must contain worker A and B
        List<WorkerTO> workers = scheduler.allWorkers();
        Assert.assertNotNull(workers);
        Assert.assertEquals(2, workers.size());
        Assert.assertEquals(workerA, workers.get(0));
        Assert.assertEquals(workerB, workers.get(1));
        Assert.assertNotSame(workers.get(0), workers.get(1));
        printWorkers(workers);

        // add a new worker
        WorkerTO newWorker = new WorkerTO();
        scheduler.registerWorker(newWorker);

        // worker must now be in the list
        workers = scheduler.allWorkers();
        Assert.assertNotNull(workers);
        Assert.assertEquals(3, workers.size());
        Assert.assertEquals(workerA, workers.get(0));
        Assert.assertEquals(workerB, workers.get(1));
        Assert.assertEquals(newWorker, workers.get(2));
        printWorkers(workers);

        // unregister a worker
        scheduler.unregisterWorker(newWorker);

        // list must have been updated
        workers = scheduler.allWorkers();
        Assert.assertNotNull(workers);
        Assert.assertEquals(2, workers.size());
        // verify list contents
        Assert.assertEquals(workerA, workers.get(0));
        Assert.assertEquals(workerB, workers.get(1));
        printWorkers(workers);
    }


    @Test
    public void jobStateUpdateTest() {
        printJobs(scheduler.allJobs());

        // expect exception
        try {
            scheduler.updateJobState(jobIdA, JobState.NEW);
            Assert.fail();
        } catch (Exception e) {
            // ok
        }

        try {
            scheduler.updateJobState(jobIdA, JobState.REMOVED);
            Assert.fail();
        } catch (Exception e) {
            // ok
        }

        try {
            scheduler.updateJobState(jobIdA, JobState.WAITING_SCHEDULED);
            Assert.fail();
        } catch (Exception e) {
            // ok
        }

        try {
            scheduler.updateJobState(jobIdA, JobState.WAITING_UNSCHEDULED);
            Assert.fail();
        } catch (Exception e) {
            // ok
        }

        // these should be allowed
        scheduler.updateJobState(jobIdA, JobState.ABORTED);
        Assert.assertEquals(JobState.ABORTED, scheduler.jobById(jobIdA).getState());

        scheduler.updateJobState(jobIdA, JobState.IN_PROGRESS);
        Assert.assertEquals(JobState.IN_PROGRESS, scheduler.jobById(jobIdA).getState());

        scheduler.updateJobState(jobIdA, JobState.COMPLETED_OK);
        Assert.assertEquals(JobState.COMPLETED_OK, scheduler.jobById(jobIdA).getState());

        // if state is set to COMPLETED*, job will be moved from queue to history
        try {
            scheduler.updateJobState(jobIdA, JobState.COMPLETED_WITH_ERRORS);
            Assert.fail();
        } catch (Exception e) {
            // should still be able to retrieve job from history
            JobTO job = scheduler.jobById(jobIdA);
            Assert.assertEquals(jobA, job);
            // ok
        }
        printJobs(scheduler.allJobs());
    }


    @Test
    public void workerStateUpdateTest() {
        // state must have been set to unknown by the scheduler
        WorkerTO worker = scheduler.workerById(workerIdA);
        Assert.assertNotNull(worker);
        Assert.assertEquals(WorkerState.UNKNOWN, worker.getState());
        worker = scheduler.workerById(workerIdB);
        Assert.assertNotNull(worker);
        Assert.assertEquals(WorkerState.UNKNOWN, worker.getState());

        // should cause an exception
        try {
            scheduler.updateWorkerState(workerIdA, WorkerState.REMOVED);
            Assert.fail();
        } catch (Exception e) {
            // ok
        }

        // should be possible
        scheduler.updateWorkerState(workerIdA, WorkerState.BUSY);
        worker = scheduler.workerById(workerIdA);
        Assert.assertEquals(WorkerState.BUSY, worker.getState());
    }


    @Test
    public void schedulingTest() {
        // TODO test ordinary job scheduling and state updating
        Assert.fail("Test not implemented");
    }


    @Test
    public void multipleWorkersTest() {
        // TODO test multiple concurrent workers, particularly for synchronisation and fail conditions!
        Assert.fail("Test not implemented");
    }
    

    private void printJobs(Collection<JobTO> items) {
        System.out.println("Jobs:");
        if ((items == null) || (items.size() == 0)) {
            System.out.println("Empty");
            return;
        }
        for (JobTO item : items)
            System.out.println("Job, id: " + item.getId()
                    + ", experiment id: " + item.getExperimentId()
                    + ", state: " + item.getState()
            );
    }


    private void printWorkers(Collection<WorkerTO> items) {
        System.out.println("Workers:");
        if ((items == null) || (items.size() == 0)) {
            System.out.println("Empty");
            return;
        }
        for (WorkerTO item : items)
            System.out.println("Worker, id: " + item.getId()
                    + ", ip: " + item.getIp()
                    + ", state: " + item.getState()
            );
    }

}
