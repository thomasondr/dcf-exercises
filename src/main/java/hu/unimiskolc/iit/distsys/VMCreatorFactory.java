package hu.unimiskolc.iit.distsys;

public class VMCreatorFactory {
	public static VMCreationApproaches createApproachesExercise()
			throws ClassNotFoundException, InstantiationException,
			IllegalAccessException {
		return (VMCreationApproaches) Class.forName(
				System.getProperty("hu.unimiskolc.iit.distsys.VMC"))
				.newInstance();
	}
}
