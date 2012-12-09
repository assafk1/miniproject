
public class Constraint {

	private ConstraintType _type;
	private Agent _agent1;
	
	public Constraint(ConstraintType _type, Agent _agent1) {
		this._type = _type;
		this._agent1 = _agent1;
	}

	public ConstraintType get_type() {
		return _type;
	}

	public Agent get_agent1() {
		return _agent1;
	}
	
	
}
