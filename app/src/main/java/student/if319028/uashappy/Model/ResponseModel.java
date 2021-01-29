package student.if319028.uashappy.Model;

import java.util.List;

public class ResponseModel {
    private boolean error;
    private String feedback;
    private  List<DataModel> hasil;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public List<DataModel> getHasil() {
        return hasil;
    }

    public void setHasil(List<DataModel> hasil) {
        this.hasil = hasil;
    }
}
