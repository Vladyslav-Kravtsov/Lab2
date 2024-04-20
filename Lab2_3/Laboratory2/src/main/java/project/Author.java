package project;
import java.io.*;

public class Author extends Human  implements Externalizable {
    public Author(String firstName, String lastName) {
        super(firstName, lastName);
    }
    public Author() {
        super();
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        super.writeExternal(out);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        super.readExternal(in);
    }
}
