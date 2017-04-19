import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.platform.snail.utils.SpringUtils;
import org.platform.snail.workflow.dao.WorkflowDao;
import org.platform.snail.workflow.model.GroupImpl;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(transactionManager = "transactionManager")
@Transactional
public class WorkflowTest extends AbstractJUnit4SpringContextTests{
	Logger logger = Logger.getLogger(this.getClass());
	@Test
	public void workflowDao() {
		WorkflowDao workflowDao = (WorkflowDao) SpringUtils.getBean("workflowDao");
		
		GroupImpl g=workflowDao.findGroupById("1");
		logger.info(g.toString());
		
	}
	public static void main(String args[]) {
		Logger logger = Logger.getLogger(WorkflowTest.class);
		WorkflowDao workflowDao = (WorkflowDao) SpringUtils.getBean("workflowDao");
		
		GroupImpl g=workflowDao.findGroupById("1");
		logger.info(g.toString());
		
	}
}
