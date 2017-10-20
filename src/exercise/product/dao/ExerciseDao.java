package exercise.product.dao;

import de.hybris.platform.core.model.c2l.LanguageModel;
import de.hybris.platform.core.model.product.ProductModel;
import java.util.List;

public interface ExerciseDao 
{
  public List<ProductModel> getProductsByRange(Double rangeLow , Double rangeHigh);
  public Set<String> getBadwords();
}


