/*
 * Gromit: SchedulerService.java
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

import java.util.List;

import org.seamless_if.processing.scheduler.JobState;
import org.seamless_if.processing.scheduler.WorkerState;
import org.seamless_if.processing.scheduler.dto.JobTO;
import org.seamless_if.processing.scheduler.dto.ModelChainInfoTO;
import org.seamless_if.processing.scheduler.dto.WorkerTO;
import org.seamless_ip.services.dao.utils.Dao;

/**
 * Interface for the Scheduler Service (DAO). Defines the contract for
 * the implementation.
 */
public interface SchedulerService extends Dao {

    public List<JobTO> allJobs();

    public JobTO jobById(String jobId);

    public JobTO addJob(JobTO newJob);

    public JobTO addJob(Long experimentId, String modelChainId);
    
    public List<WorkerTO> allWorkers();

    public WorkerTO workerById(String workerId);

    public WorkerTO registerWorker(WorkerTO newWorker);

    public WorkerTO registerWorker(String workerIp, String workerName);
    
    public void unregisterWorker(WorkerTO existingWorker);

    public void unregisterWorker(String workerId);
    
    public ModelChainInfoTO registerModelChainForWorker(String workerId, String modelChainName, String modelChainVersion);
    
    public ModelChainInfoTO unregisterModelChainForWorker(String workerId, String modelChainId);
    
    public List<ModelChainInfoTO> allCurrentlyKnownModelChains();

    public JobTO findJobForWorker(String workerId);

    public void updateWorkerState(String workerId, WorkerState newState);

    public void updateJobState(String jobId, JobState newState);

    public void clear();
}
