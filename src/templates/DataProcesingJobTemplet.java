package templates;
import job.Job;
public class DataProcesingJobTemplet implements JobPrototype {

    private final String name;
    private final String config;
    private final String type ;

    public DataProcesingJobTemplet (String name, String config , String type) {
        this.name = name;
        this.config = config;
        this.type = type;
    }

    @Override
    public Job cloneJob() {
        return new Job("JOB-" + System.nanoTime(), type , name, config);
    }
}