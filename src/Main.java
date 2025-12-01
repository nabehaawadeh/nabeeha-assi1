import connection.ConnectionPool;
import connection.ConnectionProvider;
import executor.JobExecutor;
import executor.Proxy;
import job.Job;
import user.User;
import templates.JobPrototype;
import templates.EmailJobTemplate;
import templates.DataProcesingJobTemplet;
import templates.ReportJobTemplate;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws Exception {

        ConnectionProvider provider = new ConnectionProvider();

        ConnectionPool pool = new ConnectionPool(provider);

        JobExecutor executor = new JobExecutor(pool);

        User user = new User("Noor", Arrays.asList("EMAIL", "DATA", "REPORT"));

        Proxy proxy = new Proxy(executor, pool, user);

        Job job = new EmailJobTemplate("MonthlyEmail", "format=HTML", "EMAIL").cloneJob();
        job.setRequestedBy(user);


        proxy.executeJob("EMAIL");

        proxy.executeJobInternal("EMAIL");
    }
}