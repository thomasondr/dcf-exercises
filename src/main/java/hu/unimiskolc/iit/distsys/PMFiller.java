package hu.unimiskolc.iit.distsys;

import hu.mta.sztaki.lpds.cloud.simulator.Timed;
import hu.mta.sztaki.lpds.cloud.simulator.iaas.IaaSService;
import hu.mta.sztaki.lpds.cloud.simulator.iaas.constraints.AlterableResourceConstraints;
import hu.mta.sztaki.lpds.cloud.simulator.iaas.constraints.ResourceConstraints;
import hu.mta.sztaki.lpds.cloud.simulator.iaas.statenotifications.VMStateChangeNotificationHandler;
import hu.mta.sztaki.lpds.cloud.simulator.io.VirtualAppliance;
import hu.unimiskolc.iit.distsys.interfaces.FillInAllPMs;
import hu.unimiskolc.iit.distsys.interfaces.VMCreationApproaches;

public class PMFiller  implements FillInAllPMs {

	@Override
	public void filler(IaaSService iaas, int vmCount) {
		
		VirtualAppliance va = new VirtualAppliance("VA", 1, 0);
		ResourceConstraints rc = new AlterableResourceConstraints(10, 20, 4096);
		
		for (int i=0;i<10;i++) {
			try {
				
				iaas.machines.get(i).localDisk.registerObject(va);
				iaas.registerHost(iaas.machines.get(i));
				iaas.requestVM(va,rc,iaas.machines.get(i).localDisk,iaas.machines.size());
								
				
				Timed.simulateUntilLastEvent();

				
			}
			catch (Exception e) {
				
			}
		}
		

		
	}

}
