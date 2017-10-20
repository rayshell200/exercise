package exercise.product.services;

import de.hybris.platform.core.model.c2l.LanguageModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.customerreview.impl.CustomerReviewModel;
import de.hybris.platform.customerreview.impl.UserModel;

import java.util.List;

public interface ExerciseService  
{
  
  public abstract List<ProductModel> getProductsByRange(Double rangeLow , Double rangeHigh);
  
 
}


 