package exercise.product.services.impl;

import de.hybris.platform.core.model.c2l.LanguageModel;
import de.hybris.platform.core.model.product.ProductModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import exercise.product.constants.ExerciseContants;
import org.springframework.beans.factory.annotation.Required;


public class ExerciseServiceImpl extends DefaultCustomerReviewService implements ExerciseService {
	private ExcerciseDao excerciseDao;

	private Set<String> badWords ;
	
	protected ExcerciseDao getExcerciseDao() {
		  return this.excerciseDao;
	}

	@Required
	public void setExcerciseDao(ExcerciseDao excersieDao) {
		 this.excerciseDao = excerciseDao;
	}

	public List<ProductModel> getProductsByRange(Double rangeLow, Double rangeHigh) {
		 return (List) getExcerciseDao().getProductsByRange(rangeLow, rangeHigh);
	}

	@Override
	public CustomerReviewModel createCustomerReview(Double rating, String headline, 
					String comment, UserModel user, ProductModel product) throws ExerciseBusinessException
			{
				if( badWords == null) {
					// if spring ecache is available,  baswords should be kept in cache.
					badWords = getExcerciseDao().getBadwords();
				}
				if (hasBadWord(comment,badWords)) {
					throws new ExerciseBusinessException("Bad word found!");
				}
				if (rating<0D) {
					throws new ExerciseBusinessException("rating is less than 0!");
				}
				super.createCustomerReview( rating,  headline,  comment,  user,  product);
			}

	private boolean hasBadWord(String comment, Set<String> badWords) {
		String commentLower = comment.toLowerCase();
		for (String badWord : badWords) {
			if (commentLower.contains(badWord)) {
				return true;
			}
		}
		return false;
	}

}
