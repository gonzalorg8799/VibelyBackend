package com.metrica.vibely.data.service;

/**
 * <h1>CRUD Service</h1>
 * 
 * <p>Service with the basic CRUD methods</p>
 * <ul>
 *  <li>Create</li>
 *  <li>Read</li>
 *  <li>Update</li>
 *  <li>Delete</li>
 * </ul>
 * 
 * @since 2023-11-27
 * @version 1.0
 * @author Adrian
 */
abstract interface CrudService<DTO, ID> {

    /**
     * Fetches an object by its identifier.
     *
     * @param id the object identifier
     * @return the object DTO
     * @throws NoSuchElementException
     */
    DTO getById(ID id);

    /**
     * Creates an object using provided information.
     * 
     * @param dto the information to create
     * @return the object DTO
     */
    DTO create(DTO dto);

    /**
     * Updates an object using provided information.
     * 
     * @param dto the information to update
     * @return the object DTO
     */
    DTO update(DTO dto);

    /**
     * Deletes an object by its identifier.
     * 
     * @param id the object identifier
     * @throws NoSuchElementException
     */
    void deleteById(ID id);

}