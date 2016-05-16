package org.sms.entity;

/**
 * @author Sunny
 */
public class Doctor {

  private String name;
  
  private String id;
  
  public Doctor(String name, String id) {
    this.name = name;
    this.id = id;
  }

  public final String getName() {
    return name;
  }

  public final void setName(String name) {
    this.name = name;
  }

  public final String getId() {
    return id;
  }

  public final void setId(String id) {
    this.id = id;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Doctor other = (Doctor) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    if (name == null) {
      if (other.name != null)
        return false;
    } else if (!name.equals(other.name))
      return false;
    return true;
  }
}