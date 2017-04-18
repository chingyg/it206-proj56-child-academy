package child.model;

public class ScholarshipChild extends Child
{

	private String organization;
	private double discount;

	public ScholarshipChild(int age, String name, String parentName, String parentNumber)
	{
		super(age, name, parentName, parentNumber);
		this.organization = "";
		this.discount = 0.0;
	}

	public String getOrganization()
	{
		return organization;
	}

	public void setOrganization(String organization)
	{
		this.organization = organization;
	}

	public double getDiscount()
	{
		return discount;
	}

	public void setDiscount(double discount)
	{
		this.discount = discount / 100;
	}

}