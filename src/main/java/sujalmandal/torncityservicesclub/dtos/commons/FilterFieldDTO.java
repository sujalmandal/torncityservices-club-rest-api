package sujalmandal.torncityservicesclub.dtos.commons;

import java.util.Objects;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FilterFieldDTO {

    private String type;
    private String name;
    private String groupName;
    private String value;

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	FilterFieldDTO other = (FilterFieldDTO) obj;
	if (groupName == null) {
	    if (other.groupName != null)
		return false;
	} else if (!groupName.equals(other.groupName))
	    return false;
	return true;
    }

    @Override
    public int hashCode() {
	return Objects.hash(groupName);
    }

}