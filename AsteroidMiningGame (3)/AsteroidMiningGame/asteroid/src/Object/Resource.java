package Object;


public abstract class Resource {
	private boolean IsRadioActive;
	public Resource(boolean IsRadioActive)
	{
		this.IsRadioActive = IsRadioActive;
	}
	public boolean getIsRadioActive() {
		return IsRadioActive;
	}
	
	public abstract String getType();

}