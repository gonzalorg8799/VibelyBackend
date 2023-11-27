package com.metrica.vibely.data.service;

/**
 * <h1>Generic Service</h1>
 * 
 * @since 2023-11-27
 * @version 1.0
 * @author Adrian,
 */
abstract interface CrudService <DTO, ID>  {
	
	/**
	 * gets object information by its id
	 * @param ID
	 * @return DTO
	 * @throws NoSuchElementException
	 */
	DTO getById(ID id);
	
	 /**
     * creates a new object and adds it to the database
     * @param DTO
     * @return DTO
     * @throws NoSuchElementException
     */
    DTO create(DTO dto);

    /**
     * updates an existing object in database
     * @param DTO
     * @return DTO
     * @throws NoSuchElementException
     */
    DTO update(DTO dto);

	/**
	 * deletes a object given its Id
	 * @throws NoSuchElementException
	 */
    void deleteById(ID id);
   
}
