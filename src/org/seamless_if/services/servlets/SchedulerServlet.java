/*
 * Gromit: SchedulerServlet.java
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

package org.seamless_if.services.servlets;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.dom.DOMElement;
import org.dom4j.io.XMLWriter;
import org.seamless_if.processing.scheduler.JobState;
import org.seamless_if.processing.scheduler.WorkerState;
import org.seamless_if.processing.scheduler.dto.JobTO;
import org.seamless_if.processing.scheduler.dto.ModelChainInfoTO;
import org.seamless_if.processing.scheduler.dto.WorkerTO;
import org.seamless_if.processing.sofa.SeamException;
import org.seamless_if.services.scheduler.SchedulerService;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * Servlet implementation class Scheduler.
 */
public class SchedulerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private SchedulerService schedulerDao;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SchedulerServlet() {
        super();
    }
    
    
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		WebApplicationContext springContext = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		schedulerDao = (SchedulerService) springContext.getBean("schedulerDao");
		
		// TODO remove test code
		
		// create some sample model chains
		ModelChainInfoTO chainA = new ModelChainInfoTO();
		chainA.setName("APES->FSSIM");
		chainA.setVersion("1.0");
		ModelChainInfoTO chainB = new ModelChainInfoTO();
		chainB.setName("FSSIM<->CAPRI");
		chainB.setVersion("1.1");
		
		// create some sample workers
		WorkerTO workerA = new WorkerTO();
		workerA.setName("WorkerA");
		workerA.addAvailableModelChain(chainA);
		schedulerDao.registerWorker(workerA);
		WorkerTO workerB = new WorkerTO();
		workerB.setName("WorkerB");
		workerB.addAvailableModelChain(chainA);
		workerB.addAvailableModelChain(chainB);
		schedulerDao.registerWorker(workerB);
		
		// create some sample jobs
		JobTO jobA = new JobTO();
		jobA.setExperimentId(1L);
		jobA.setModelChain(chainA);
		schedulerDao.addJob(jobA);
		JobTO jobB = new JobTO();
		jobB.setExperimentId(2L);
		jobB.setModelChain(chainB);
		schedulerDao.addJob(jobB);
	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		handleRequest(request, response);
	}

	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		handleRequest(request, response);
	}

	
	@SuppressWarnings("unchecked")
	private void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// set-up some things for output as XML
		response.setContentType("text/xml");
		XMLWriter writer = new XMLWriter(response.getOutputStream());
		Element root = new DOMElement("scheduler");
        Document document = DocumentHelper.createDocument(root);
        
        // parse arguments to figure out what to do
        String methodName = request.getParameter("method");
        if (methodName == null)
        	methodName = "info";
        
        // invoke method based on reflection (names must match!)
        try {
        	if (ACCESSIBLE_METHODS.valueOf(methodName) == null)
        		throw new IllegalAccessException();
        	Method method = this.getClass().getMethod(methodName, Element.class, HttpServletRequest.class);
        	method.invoke(this, root, request);
        } catch (Exception e) {
        	root.add(new DOMElement("error").addText(e.toString()));
        	root.add(new DOMElement("method").addText(methodName));
        	Map<String, String[]> parameters = (Map<String, String[]>)request.getParameterMap();
        	for (Object key : parameters.keySet()) {
        		Element param = new DOMElement("parameter").addAttribute("name", key.toString());
        		Element valuesNode = new DOMElement("values");
        		String[] values = parameters.get(key);
        		for (String value : values) {
        			valuesNode.add(new DOMElement("value").addText(value));
        		}
        		param.add(valuesNode);
        		root.add(param);
        	}
			e.printStackTrace();
        }

        // write the output
        try {
            writer.write(document);
            writer.close();
        }
        catch (Exception ex) {
            throw new SeamException(ex, "Failed to create servlet xml output! Error: %s", ex.getMessage());
        }
	}

	
	/**
	 * List of accessible methods that can be called through the servlet. Note
	 * that they all must have the same signature and produce output as XML
	 * added to the root node, based on the parameters from the request. 
	 */
	private enum ACCESSIBLE_METHODS {
		info, // no param 
		allJobs, // no param
		jobById, // jid (job id)
		allWorkers, // no param
		workerById, // wid (worker id)
		allKnownChains, // no param
		addJob, // eid (experiment id), cid (chain id)
		registerWorker, // ip, name
		unregisterWorker, // wid (worker id)
		registerChain, // wid (worker id), name, version
		unregisterChain, // wid (worker id), cid (chain id)
		jobForWorker, // wid (worker id)
		setWorkerState, // wid (worker id), ws (worker state name)
		setJobState, // jid (job id), js (job state name)
		workerStates, // no param
		jobStates // no param
	}

	
	public void info(Element root, HttpServletRequest request) {
		allJobs(root, request);
		allWorkers(root, request);
		allKnownChains(root, request);
		workerStates(root, request);
		jobStates(root, request);
	}
	
	
	public void allJobs(Element root, HttpServletRequest request)
	{
		Element node = new DOMElement("jobs");
		List<JobTO> jobs = schedulerDao.allJobs();
		for (JobTO job : jobs) {
			node.add(job.toXml());
		}
		root.add(node);
	}
	
	
	public void jobById(Element root, HttpServletRequest request)
	{
		Element node = new DOMElement("job");
		String id = request.getParameter("jid");
		if (id == null) {
			throw new SeamException("Method requires additional parameter: jid (job id)");
		} else {
			JobTO obj = schedulerDao.jobById(id);
			if (obj != null)
				node.add(obj.toXml());
		}
		root.add(node);
	}
	
	
	public void allKnownChains(Element root, HttpServletRequest request)
	{
		Element node = new DOMElement("chains");
		List<ModelChainInfoTO> chains = schedulerDao.allCurrentlyKnownModelChains();
		for (ModelChainInfoTO chain : chains) {
			node.add(chain.toXml());
		}
		root.add(node);
	}

	
	public void allWorkers(Element root, HttpServletRequest request)
	{
		Element node = new DOMElement("workers");
        List<WorkerTO> workers = schedulerDao.allWorkers();
		for (WorkerTO worker : workers) {
			node.add(worker.toXml());
		}
        root.add(node);
	}
	
	
	public void workerById(Element root, HttpServletRequest request)
	{
		Element node = new DOMElement("worker");
		String id = request.getParameter("wid");
		
		if (id == null) {
			throw new SeamException("Method requires additional parameter: wid (worker id)");
		} else {
			WorkerTO obj = schedulerDao.workerById(id);
			if (obj != null)
				node.add(obj.toXml());
		}
		root.add(node);
	}
	
	
	public void addJob(Element root, HttpServletRequest request)
	{
		String experimentId = request.getParameter("eid");
		String modelChainId = request.getParameter("cid");
		
		if ((experimentId == null) || (modelChainId == null)) {
			throw new SeamException("Method requires additional parameters: eid (experiment id), cid (chain id)");
		}
		
		JobTO job = schedulerDao.addJob(Long.valueOf(experimentId), modelChainId);
		if (job == null) {
			throw new SeamException("Failed to create new job, please see server log for cause!");
		} else {
			Element node = new DOMElement("job");
			node.add(job.toXml());
			root.add(node);
		}
	}
	

	public void registerWorker(Element root, HttpServletRequest request)
	{
		// TODO can we get the IP of the requester in another way?
		String workerIp = request.getParameter("ip");
		String workerName = request.getParameter("name");
		if ((workerIp == null) || (workerName == null)) {
			throw new SeamException("Method requires additional parameters: ip, name");
		}
		
		WorkerTO worker = schedulerDao.registerWorker(workerIp, workerName);
		if (worker == null) {
			throw new SeamException("Failed to register worker node, please see server log for cause!");
		} else {
			Element node = new DOMElement("worker");
			node.add(worker.toXml());
			root.add(node);
		}
	}
	
	
	public void unregisterWorker(Element root, HttpServletRequest request)
	{
		Element node = new DOMElement("worker");
		String id = request.getParameter("wid");
		
		if (id == null) {
			throw new SeamException("Method requires additional parameter: wid (worker id)");
		} else {
			WorkerTO obj = schedulerDao.workerById(id);
			if (obj != null) {
				schedulerDao.unregisterWorker(id);
				node.add(obj.toXml());
			} else {
				throw new SeamException("Failed to unregister worker node, no worker registered with id: " + id);
			}
		}
		root.add(node);
	}

	
	public void registerChain(Element root, HttpServletRequest request)
	{
		String workerId = request.getParameter("wid");
		String modelChainName = request.getParameter("name");
		String modelChainVersion = request.getParameter("version");
		
		if ((workerId == null) || (modelChainName == null) || (modelChainVersion == null)) {
			throw new SeamException("Method requires additional parameters: wid (worker id), name (of chain), version (of chain)");
		}

		ModelChainInfoTO mci = schedulerDao.registerModelChainForWorker(workerId, modelChainName, modelChainVersion);
		if (mci == null) {
			throw new SeamException("Failed to register model chain for worker, please see server log for cause!");
		} else {
			Element node = new DOMElement("chain");
			node.add(mci.toXml());
			root.add(node);
		}
	}

	
	public void unregisterChain(Element root, HttpServletRequest request)
	{
		String workerId = request.getParameter("wid");
		String modelChainId = request.getParameter("cid");

		if ((workerId == null) || (modelChainId == null)) {
			throw new SeamException("Method requires additional parameters: wid (worker id), cid (chain id)");
		}

		ModelChainInfoTO mci = schedulerDao.unregisterModelChainForWorker(workerId, modelChainId);
		if (mci == null) {
			throw new SeamException("Failed to unregister model chain for worker, please see server log for cause!");
		} else {
			Element node = new DOMElement("chain");
			node.add(mci.toXml());
			root.add(node);
		}
	}
	
	
	public void jobForWorker(Element root, HttpServletRequest request)
	{
		String workerId = request.getParameter("wid");

		if (workerId == null) {
			throw new SeamException("Method requires additional parameter: wid (worker id)");
		}
		
		JobTO job = schedulerDao.findJobForWorker(workerId);
		if (job == null) {
			throw new SeamException("Failed to find job for worker, please see server log for cause!");
		} else {
			Element node = new DOMElement("job");
			node.add(job.toXml());
			root.add(node);
		}
	}
	
	
	public void setWorkerState(Element root, HttpServletRequest request)
	{
		String workerId = request.getParameter("wid");
		String workerStateName = request.getParameter("ws");
		
		if ((workerId == null) || (workerStateName == null)) {
			throw new SeamException("Method requires additional parameters: wid (worker id), ws (worker state name)");
		}

		try {
			WorkerState newState = WorkerState.valueOf(workerStateName);
			schedulerDao.updateWorkerState(workerId, newState);
			Element node = new DOMElement("worker");
			WorkerTO worker = schedulerDao.workerById(workerId);
			node.add(worker.toXml());
			root.add(node);
		} catch (Exception ex) {
			throw new SeamException("Failed to update state of worker, please see server log for cause!");
		}
	}
	
	
	public void workerStates(Element root, HttpServletRequest request)
	{
		Element node = new DOMElement("workerStates");
		for (WorkerState state : WorkerState.values()) {
			node.add(new DOMElement("state").addText(state.name()));
		}
        root.add(node);
	}
	
	
	public void setJobState(Element root, HttpServletRequest request)
	{
		String jobId = request.getParameter("jid");
		String jobStateName = request.getParameter("js");
		
		if ((jobId == null) || (jobStateName == null)) {
			throw new SeamException("Method requires additional parameters: jid (job id), js (job state name)");
		}

		try {
			JobState newState = JobState.valueOf(jobStateName);
			schedulerDao.updateJobState(jobId, newState);
			Element node = new DOMElement("job");
			JobTO job = schedulerDao.jobById(jobId);
			node.add(job.toXml());
			root.add(node);
		} catch (Exception ex) {
			throw new SeamException("Failed to update state of job, please see server log for cause!");
		}
	}

	
	public void jobStates(Element root, HttpServletRequest request) 
	{
		Element node = new DOMElement("jobStates");
		for (JobState state : JobState.values()) {
			node.add(new DOMElement("state").addText(state.name()));
		}
        root.add(node);
	}
	
	
    // TODO Consider for (re)implementation
    /**
     * Check the state of the specified experiment. The string returned can be:
     * <p/>
     * EXP_RUNNING EXP_QUEUED + [POSTFIX_HAS_RESULTS | POSTFIX_NO_RESULTS]
     * EXP_NOTQUEUED + [POSTFIX_HAS_RESULTS | POSTFIX_NO_RESULTS]
     *
     * @param experiment to check
     * @return String description of the state
     */
    /*
    private static String EXP_RUNNING = "Running";
    private static String EXP_QUEUED = "Queued";
    private static String EXP_NOTQUEUED = "Not Scheduled";

    private static String POSTFIX_HAS_RESULTS = ", results from previous run available";
    private static String POSTFIX_NO_RESULTS = ", no results available at this time";

    private static String POSTFIX_STATE_MISSING = " [State info missing]";
    private static String POSTFIX_STATE_UNKNOWN = " [Unknown state]";
    private static String POSTFIX_STATE_0 = " [Not calculated]";
    private static String POSTFIX_STATE_1 = " [Last calculation aborted]";
    private static String POSTFIX_STATE_2 = " [Calculation in progress]";
    private static String POSTFIX_STATE_3 = " [Last calculation ok]";
    private static String POSTFIX_STATE_4 = " [Last calculation failed]";


    public synchronized String query(Experiment experiment) {
        StringBuffer output = new StringBuffer();

        // check if the experiment is currently running
        if ((_actualExperiment.getExperiment() != null)
                && (_actualExperiment.getExperiment().equals(experiment)))
            return EXP_RUNNING;

        // check if it is in the queue or not
        if (_expQueue.indexOf(experiment) >= 0)
            output.append(EXP_QUEUED);
        else
            output.append(EXP_NOTQUEUED);

        // include results availability in message
        if ((experiment != null) && (experiment.getIndicatorValues() != null)
                && (experiment.getIndicatorValues().size() > 0))
            output.append(POSTFIX_HAS_RESULTS);
        else
            output.append(POSTFIX_NO_RESULTS);

        // include state of QueuedExperiment in message
        if ((experiment != null) && (experiment.getState() != null)) {
            switch (experiment.getState()) {
                case 0:
                    output.append(POSTFIX_STATE_0);
                    break;
                case 1:
                    output.append(POSTFIX_STATE_1);
                    break;
                case 2:
                    output.append(POSTFIX_STATE_2);
                    break;
                case 3:
                    output.append(POSTFIX_STATE_3);
                    break;
                case 4:
                    output.append(POSTFIX_STATE_4);
                    break;
                default:
                    output.append(POSTFIX_STATE_UNKNOWN);
            }
        } else
            output.append(POSTFIX_STATE_MISSING);

        return output.toString();
    }
    */
}
