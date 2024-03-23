package aibles.api_6.dto;

public class StudentResponse extends StudentRequest {
    private String id;

    public StudentResponse() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "StudentResponse{" +
                "id='" + id + '\'' +
                ", name='" + getName() + '\'' +
                ", password='" + getPassword() + '\'' +
                ", lastname='" + getLastname() + '\'' +
                '}';
    }
}
