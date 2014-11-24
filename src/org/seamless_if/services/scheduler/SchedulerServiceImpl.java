/*
 * Gromit: SchedulerServiceImp.java
 * ==============================================================================
 * This work has been carried out as part of the SEAMLESS Integrated Framework
 * project, EU 6th Framework Programme, contract no. 010036-2 and/or as part
 * of the SEAMLESS association.
 *
 * Copyright (c) 2009 The SEAMLESS Association.
 *
 * For more information: http://www.seamlessassociation.org;
 * email: info@seamless-if.org
 *
 * The contents of this file is subject to the SEAMLESS Association License for
 * software infrastructure and model components Version 1.1 (the "License");
 * you may not use this file except in compliance with the License. You may
 * obtain a copy of the License at http://www.seamlessassociation.org/License.htm
 *
 * Software distributed under the License is distributed on an "AS IS"  basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for
 * the specific governing rights and limitations.
 * ================================================================================
 */

package org.seamless_if.services.scheduler;

import java.util.ArrayList;
import java.util.List;

import org.seamless_if.processing.scheduler.Job;
import org.seamless_if.processing.scheduler.JobState;
import org.seamless_if.processing.scheduler.ModelChainInfo;
import org.seamless_if.processing.scheduler.Scheduler;
import org.seamless_if.processing.scheduler.WorkerInfo;
import org.seamless_if.processing.scheduler.WorkerState;
import org.seamless_if.processing.scheduler.dto.JobTO;
import org.seamless_if.processing.scheduler.dto.ModelChainInfoTO;
import org.seamless_if.processing.scheduler.dto.WorkerTO;

/**
 * Implementation of the Scheduler Service DAO. For now it will not
 * use Hibernate database based OR/M, but serve as the access point to the
 * singleton Scheduler instance. This instance takes care of loading and
 * saving of data some way.
 *
 * @author Rob Knapen; Alterra, Wageningen UR, NL
 */
public class SchedulerServiceImpl implements SchedulerService {

    /**
     * Access to the Scheduler singleton instance.
     *
     * @return Scheduler
     */
    // TODO does this method need to be synchronised?
    private Scheduler getScheduler() {
        return Scheduler.INSTANCE;
    }


    public List<JobTO> allJobs() {
        List<Job> jobs = getScheduler().getAllJobsFromQueue();

        ArrayList<JobTO> result = new ArrayList<JobTO>();
        for (Job job : jobs) {
            result.add(new JobTO(job));
        }

        return result;
    }


    public JobTO jobById(String jobId) {
        Job obj = getScheduler().getJob(jobId);
        if (obj == null) {
            return null;
        } else {
            return new JobTO(obj);
        }
    }


    public JobTO addJob(JobTO newJob) {
    	Job addedJob = getScheduler().addJob(newJob.toJob());
    	if (addedJob != null) 
    		return new JobTO(addedJob);
    	else
    		return null;
    }

    
    public JobTO addJob(Long experimentId, String modelChainId) {
    	Job addedJob = getScheduler().addJob(experimentId, modelChainId);
    	if (addedJob != null) 
    		return new JobTO(addedJob);
    	else
    		return null;
    }
    
    
    public ModelChainInfoTO registerModelChainForWorker(String workerId, String modelChainName, String modelChainVersion) {
    	ModelChainInfo info = getScheduler().registerModelChainForWorker(workerId, modelChainName, modelChainVersion);
    	if (info != null)
    		return new ModelChainInfoTO(info);
    	else
    		return null;
    }
    
    
    public ModelChainInfoTO unregisterModelChainForWorker(String workerId, String modelChainId) {
    	ModelChainInfo info = getScheduler().unregisterModelChainForWorker(workerId, modelChainId);
    	if (info != null)
    		return new ModelChainInfoTO(info);
    	else
    		return null;
    }
    
    
    public List<ModelChainInfoTO> allCurrentlyKnownModelChains() {
    	List<ModelChainInfo> modelChainInfos = getScheduler().getAllKnownModelChainsInfo();
    	
    	ArrayList<ModelChainInfoTO> result = new ArrayList<ModelChainInfoTO>();
    	for (ModelChainInfo info : modelChainInfos) {
    		result.add(new ModelChainInfoTO(info));
    	}
    	
    	return result;
    }


    public List<WorkerTO> allWorkers() {
        List<WorkerInfo> workerInfos = getScheduler().getAllWorkers();

        ArrayList<WorkerTO> result = new ArrayList<WorkerTO>();
        for (WorkerInfo workerInfo : workerInfos) {
            result.add(new WorkerTO(workerInfo));
        }

        return result;
    }


    public WorkerTO workerById(String workerId) {
        WorkerInfo obj = getScheduler().getWorker(workerId);
        if (obj == null) {
            return null;
        } else {
            return new WorkerTO(obj);
        }
    }


    public WorkerTO registerWorker(WorkerTO newWorker) {
        WorkerInfo addedWorker = getScheduler().registerWorker(newWorker.toWorker());
        if (addedWorker != null)
        	return new WorkerTO(addedWorker);
        else
        	return null;
    }

    
    public WorkerTO registerWorker(String workerIp, String workerName) {
    	WorkerInfo addedWorker = getScheduler().registerWorker(workerIp, workerName);
        if (addedWorker != null)
        	return new WorkerTO(addedWorker);
        else
        	return null;
    }
    

    public void unregisterWorker(WorkerTO existingWorker) {
        getScheduler().unregisterWorker(existingWorker.getId());
    }

    
    public void unregisterWorker(String workerId) {
    	getScheduler().unregisterWorker(workerId);
    }

    
    public JobTO findJobForWorker(String workerId) {
        Job obj = getScheduler().getJobForWorker(workerId);
        if (obj == null) {
            return null;
        } else {
            return new JobTO(obj);
        }
    }


    public void updateWorkerState(String workerId, WorkerState newState) {
        getScheduler().updateWorkerState(workerId, newState);
    }


    public void updateJobState(String jobId, JobState newState) {
        getScheduler().updateJobState(jobId, newState);
    }


    public void clear() {
        getScheduler().clear();
    }

}
