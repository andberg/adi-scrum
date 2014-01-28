package se.adi.floggit.classes;

public final class User
{
	private final String email;
	private final String password;
	private final String firstname;
	private final String surname;
	private final String streetAddress;
	private final String postcode;
	private final String town;
	private final String phonenumber;

	public User(String email, String password, String firstname, String surname,
			String streetAddress, String postcode, String town, String phonenumber)
	{
		this.email = email;
		this.password = password;
		this.firstname = firstname;
		this.surname = surname;
		this.streetAddress = streetAddress;
		this.postcode = postcode;
		this.town = town;
		this.phonenumber = phonenumber;
	}

	public User(String email, String password, String firstname, String surname,
			String streetAddress, String postcode, String town)
	{
		this.email = email;
		this.password = password;
		this.firstname = firstname;
		this.surname = surname;
		this.streetAddress = streetAddress;
		this.postcode = postcode;
		this.town = town;
		phonenumber = null;
	}

	public String getEmail()
	{
		return email;
	}

	public String getPassword()
	{
		return password;
	}

	public String getFirstname()
	{
		return firstname;
	}

	public String getSurname()
	{
		return surname;
	}

	public String getStreetAddress()
	{
		return streetAddress;
	}

	public String getPostcode()
	{
		return postcode;
	}

	public String getTown()
	{
		return town;
	}

	public String getPhonenumber()
	{
		return phonenumber;
	}

	public String toString()
	{
		String string;
		string = "Email / Username: " + getEmail() + "\nPassword: " + getPassword() +
				"\nName: " + getFirstname() + " " + getSurname() +
				"\nAddress: " + getStreetAddress() + " " + getPostcode() + " " + getTown() +
				"\nPhonenumber: " + getPhonenumber() + "\n";
		return string;
	}
}
