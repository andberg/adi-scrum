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

	public String toString()
	{
		String string;
		string = "Email / Username: " + email + "\nPassword: " + password +
				"\nName: " + firstname + " " + surname +
				"\nAddress: " + streetAddress + " " + postcode + " " + town +
				"\nPhonenumber: " + phonenumber + "\n";
		return string;
	}
}
