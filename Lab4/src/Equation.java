import java.io.Serial;
import java.io.Serializable;


public abstract class Equation implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    public abstract void solve();

    public abstract void input();

    public abstract void display();
}
