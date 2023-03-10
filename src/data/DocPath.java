package data;


/**
 * Essential data classes
 */
final public class DocPath {
    private final String path;

    public DocPath(String docPath) {
        checkDocPath(docPath);
        this.path = docPath;
    }

    private void checkDocPath(String docPath) {
        if (docPath == null) throw new NullPointerException("El path del documento es NULL ");
    }

    public String getDocPath() {
        return this.path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DocPath doc_path = (DocPath) o;
        return path.equals(doc_path.path);
    }

    @Override
    public int hashCode() {
        return path.hashCode();
    }

    @Override
    public String toString() {
        return "DocPath{" + "path documento='" + path + '\'' + '}';
    }
}