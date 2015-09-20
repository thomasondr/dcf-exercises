package hu.unimiskolc.iit.distsys.forwarders;

import hu.mta.sztaki.lpds.cloud.simulator.energy.powermodelling.PowerState;
import hu.mta.sztaki.lpds.cloud.simulator.iaas.PhysicalMachine;
import hu.mta.sztaki.lpds.cloud.simulator.iaas.VirtualMachine;
import hu.mta.sztaki.lpds.cloud.simulator.iaas.constraints.ResourceConstraints;
import hu.mta.sztaki.lpds.cloud.simulator.io.NetworkNode.NetworkException;
import hu.mta.sztaki.lpds.cloud.simulator.io.Repository;
import hu.mta.sztaki.lpds.cloud.simulator.io.VirtualAppliance;

import java.util.EnumMap;
import java.util.HashMap;

public class PMForwarder extends PhysicalMachine implements ForwardingRecorder {
	private boolean reqVMCalled = false;
	private boolean allocVMCalled = false;
	private boolean deployVMCalled = false;

	public PMForwarder(double cores, double perCorePocessing, long memory,
			Repository disk, int onD, int offD,
			EnumMap<PowerStateKind, EnumMap<State, PowerState>> powerTransitions) {
		super(cores, perCorePocessing, memory, disk, onD, offD,
				powerTransitions);
	}

	public void resetForwardingData() {
		reqVMCalled = true;
		allocVMCalled = false;
		deployVMCalled = false;
	}

	public boolean isReqVMCalled() {
		return reqVMCalled;
	}

	public boolean isDeployVMCalled() {
		return deployVMCalled;
	}

	public boolean isAllocVMCalled() {
		return allocVMCalled;
	}

	@Override
	public VirtualMachine[] requestVM(VirtualAppliance va,
			ResourceConstraints rc, Repository vaSource, int count)
			throws hu.mta.sztaki.lpds.cloud.simulator.iaas.VMManager.VMManagementException,
			NetworkException {
		reqVMCalled = true;
		return super.requestVM(va, rc, vaSource, count);
	}

	@Override
	public VirtualMachine[] requestVM(VirtualAppliance va,
			ResourceConstraints rc, Repository vaSource, int count,
			HashMap<String, Object> schedulingConstraints)
			throws hu.mta.sztaki.lpds.cloud.simulator.iaas.VMManager.VMManagementException,
			NetworkException {
		reqVMCalled = true;
		return super.requestVM(va, rc, vaSource, count, schedulingConstraints);
	}

	@Override
	public void deployVM(VirtualMachine vm, ResourceAllocation ra,
			Repository vaSource)
			throws hu.mta.sztaki.lpds.cloud.simulator.iaas.VMManager.VMManagementException,
			NetworkException {
		deployVMCalled = true;
		super.deployVM(vm, ra, vaSource);
	}

	@Override
	public ResourceAllocation allocateResources(ResourceConstraints requested,
			boolean strict, int allocationValidityLength)
			throws hu.mta.sztaki.lpds.cloud.simulator.iaas.VMManager.VMManagementException {
		allocVMCalled = true;
		return super.allocateResources(requested, strict,
				allocationValidityLength);
	}
}
