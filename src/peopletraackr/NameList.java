package peopletraackr;

import java.io.Serializable;
import java.util.HashMap;

public class NameList implements Serializable {

	private static final long serialVersionUID = -5865222273805506831L;
	HashMap<String, Long> fullName = new HashMap<>();
	HashMap<String, Long> firstName = new HashMap<>();
	HashMap<String, Long> lastName = new HashMap<>();

	public HashMap<String, Long> getFullNames() {
		return fullName;
	}
	@SuppressWarnings("unchecked")
	public void setFullNames(HashMap<String, Long> fullName) {
		this.fullName = (HashMap<String, Long>)fullName.clone();
	}
	public HashMap<String, Long> getFirstNames() {
		return firstName;
	}
	@SuppressWarnings("unchecked")
	public void setFirstNames(HashMap<String, Long> firstName) {
		this.firstName = (HashMap<String, Long>)firstName.clone();
	}
	public HashMap<String, Long> getLastNames() {
		return lastName;
	}
	@SuppressWarnings("unchecked")
	public void setLastNames(HashMap<String, Long> lastName) {
		this.lastName = (HashMap<String, Long>)lastName.clone();
	}


	public void addLastName(String last){
		Long c = this.lastName.containsKey(last)?this.lastName.get(last)+1:1;
		this.lastName.put(last, c);
	}

	public void addFirstName(String first){
		Long c = this.firstName.containsKey(first)?this.firstName.get(first)+1:1;
		this.firstName.put(first, c);
	}

	public void addFullName(String full){
		Long c = this.fullName.containsKey(full)?this.fullName.get(full)+1:1;
		this.fullName.put(full, c);
	}


}
