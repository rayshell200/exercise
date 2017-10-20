package exercise.product.dao.impl;

import de.hybris.platform.core.model.c2l.LanguageModel;
import de.hybris.platform.core.model.product.ProductModel;
import exercise.product.dao.ExerciseDao;
import de.hybris.platform.customerreview.dao.impl.DefaultCustomerReviewDao;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;
import exercise.product.constants.ExerciseConstants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

public class ExerciseDaoImpl extends DefaultCustomerReviewDao implements ExerciseDao {
	
 	
	
	public Integer getProductsByRange(Double rangeLow, Double rangeHigh) {
		String query = "SELECT count(product) FROM {" + "CustomerReview" + "} WHERE {" + "rating" + "}>?rangeLow AND {"
				+ "rating" + "}<?rangeHigh ";
		FlexibleSearchQuery fsQuery = new FlexibleSearchQuery(query);
		fsQuery.addQueryParameter("rangeLow", rangeLow);
		fsQuery.addQueryParameter("rangeHigh", rangeHigh);
		fsQuery.setResultClassList(Collections.singletonList(ProductModel.class));

		SearchResult<Integer> searchResult = getFlexibleSearchService().search(fsQuery);
		return searchResult.getResult();
	}
	
	public Set<String> getBadWords()
	{
		 try {

			 Set<String> sb = new HashSet<String>();

			 BufferedReader br = new BufferedReader(
					 new InputStreamReader(getClass().getResourceAsStream(ExerciseConstants.BADWORS_PATH), "UTF-8")
					 );
			 for (String c = br.readLine(); c != null; c = br.readLine()) sb.add(c);
			 return sb;

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	}
}
