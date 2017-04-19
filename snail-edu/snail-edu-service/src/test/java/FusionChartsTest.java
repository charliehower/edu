import java.util.ArrayList;

import org.platform.snail.model.CategorySecondPro;
import org.platform.snail.model.CategoryThirdPro;
import org.platform.snail.model.DatasetSecondPro;
import org.platform.snail.model.DatasetThirdPro;
import org.platform.snail.model.FusionCharts;
import org.platform.snail.utils.JsonUtils;


public class FusionChartsTest {
	public static void main(String[] args) {
		CategoryThirdPro c1 = new CategoryThirdPro();
		CategoryThirdPro c2 = new CategoryThirdPro();
		CategoryThirdPro c3 = new CategoryThirdPro();
		c1.setLabel("全年专户");
		c2.setLabel("家庭账户");
		c3.setLabel("风险金账户");
		DatasetThirdPro d1 = new DatasetThirdPro();
		DatasetThirdPro d2 = new DatasetThirdPro();
		d1.setValue("34658963");
		d2.setValue("773381");
		DatasetThirdPro d3 = new DatasetThirdPro();
		DatasetThirdPro d4 = new DatasetThirdPro();
		d3.setValue("56450568");
		d4.setValue("5431092");
		CategorySecondPro s1 = new CategorySecondPro();
		DatasetSecondPro s2 = new DatasetSecondPro();
		DatasetSecondPro s3 = new DatasetSecondPro();
		ArrayList<CategoryThirdPro> category = new ArrayList<CategoryThirdPro>();
		category.add(c1);
		category.add(c2);
		category.add(c3);
		s1.setCategory(category);
		ArrayList<DatasetThirdPro> data1 = new ArrayList<DatasetThirdPro>();
		data1.add(d1);
		data1.add(d2);
		s2.setSeriesname("已使用");
		s2.setData(data1);
		ArrayList<DatasetThirdPro> data2 = new ArrayList<DatasetThirdPro>();
		data2.add(d3);
		data2.add(d4);
		s3.setSeriesname("余额");
		s3.setData(data2);
		ArrayList<CategorySecondPro> categories = new ArrayList<CategorySecondPro>();
		categories.add(s1);
		ArrayList<DatasetSecondPro> dataset = new ArrayList<DatasetSecondPro>();
		dataset.add(s2);
		dataset.add(s3);
		FusionCharts fc = new FusionCharts();
		fc.setCategories(categories);
		fc.setDataset(dataset);
		System.out.println(JsonUtils.toJSONString(fc));
	}
}
