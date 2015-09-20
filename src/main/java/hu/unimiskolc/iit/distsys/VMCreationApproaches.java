package hu.unimiskolc.iit.distsys;

public interface VMCreationApproaches {
	public void directVMCreation() throws Exception;

	public void twoPhaseVMCreation() throws Exception;

	public void indirectVMCreation() throws Exception;

	public void migratedVMCreation() throws Exception;
}
