package hu.unimiskolc.iit.distsys;

import hu.mta.sztaki.lpds.cloud.simulator.Timed;
import hu.mta.sztaki.lpds.cloud.simulator.iaas.PhysicalMachine;
import hu.mta.sztaki.lpds.cloud.simulator.iaas.PhysicalMachine.ResourceAllocation;
import hu.mta.sztaki.lpds.cloud.simulator.iaas.VirtualMachine;
import hu.mta.sztaki.lpds.cloud.simulator.iaas.constraints.AlterableResourceConstraints;
import hu.mta.sztaki.lpds.cloud.simulator.io.VirtualAppliance;

public class VMCreationClass implements VMCreationApproaches {

	@Override
	public void directVMCreation() throws Exception {

		PhysicalMachine pm = ExercisesBase.getNewPhysicalMachine();		
				
		VirtualAppliance va = new VirtualAppliance("NewVA",1,0);
		pm.localDisk.registerObject(va);
		
		pm.turnon();
		Timed.simulateUntilLastEvent();
				
		pm.requestVM(va, new AlterableResourceConstraints(0.01, 1, 1), pm.localDisk, 2);
		
		Timed.simulateUntilLastEvent();

	}

	@Override
	public void twoPhaseVMCreation() throws Exception {
		PhysicalMachine pm = ExercisesBase.getNewPhysicalMachine();		
		
		VirtualAppliance va = new VirtualAppliance("NewVA",1,0);
		pm.localDisk.registerObject(va);
		
		pm.turnon();
		
		Timed.simulateUntilLastEvent();
		
		ResourceAllocation ra1 = pm.allocateResources(new AlterableResourceConstraints(4, 8, 512), true,
				PhysicalMachine.defaultAllocLen);
		ResourceAllocation ra2 = pm.allocateResources(new AlterableResourceConstraints(4, 8, 512), true,
				PhysicalMachine.defaultAllocLen);
		
		VirtualMachine vm1 = new VirtualMachine(va);
		pm.deployVM(vm1, ra1, pm.localDisk);

		VirtualMachine vm2 = new VirtualMachine(va);
		pm.deployVM(vm2, ra2, pm.localDisk);
		
		Timed.simulateUntilLastEvent();
		
	}

	@Override
	public void indirectVMCreation() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void migratedVMCreation() throws Exception {
		// TODO Auto-generated method stub
		
	}

}
