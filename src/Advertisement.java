import java.io.Serializable;
import java.time.LocalDateTime;

public class Advertisement implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String email;
    private String title;
    private String body;
    private LocalDateTime time;

    Advertisement(String name, String email, String title, String body){
        this.name = name;
        this.email = email;
        this.title = title;
        this.body = body;
        this.time = LocalDateTime.now();
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }
    public LocalDateTime getTime() {
        return time;
    }
    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
