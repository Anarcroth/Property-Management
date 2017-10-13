package com.property.manager.dao.impl;

import com.property.manager.dao.IPropertyDAO;
import com.property.manager.models.Property;
import com.property.manager.rowmappers.PropertyRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by EKAC on 12.10.2017 г..
 */

@Transactional
@Repository
public class PropertyDAO implements IPropertyDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public List<Property> getAllProperties() {
        String sql = "SELECT * FROM property";
        //RowMapper<Article> rowMapper = new BeanPropertyRowMapper<Article>(Article.class);
        RowMapper<Property> rowMapper = new PropertyRowMapper();
        return this.jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public Property getPropertyById(int propertyId) {
        return null;
    }

    @Override
    public void addProperty(Property property) {

    }
}
