package se331.lab.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import se331.lab.rest.dao.OrganizationDao;
import se331.lab.rest.entity.Organization;

@Service
public class OrganizationServiceImpl implements OrganizationService{
    @Autowired
    OrganizationDao organizationDao;

    @Override
    public Integer getOrganizationSize() { return organizationDao.getOrganizationSize(); }

    @Override
    public Page<Organization> getOrganizations(Integer pageSize, Integer page) {
        return organizationDao.getOrganizations(pageSize, page);
    }

    @Override
    public Organization getOrganization(Long id) {
        return organizationDao.getOrganization(id);
    }

    @Override
    public Organization save(Organization organization) {
        return organizationDao.save(organization);
    }
}
