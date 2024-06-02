package com.ing.store.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Constants {
  public static final String PRODUCT_NOT_FOUND_EXCEPTION = "Product not found for id: ";
  public static final String DELETED_PRODUCT = "Deleted product with id: ";
  public static final String ADDING_PRODUCT_LOG_MESSAGE = "Adding product: {}";
  public static final String FINDING_PRODUCT_LOG_MESSAGE = "Finding product with ID: {}";
  public static final String RETRIEVE_ALL_PRODUCTS_LOG_MESSAGE = "Retrieving all products";
  public static final String UPDATING_PRICE_LOG_MESSAGE = "Updating price for product with ID: {} to {}";
  public static final String SECRET_KEY = "secret_key";
}
