package se331.lab.rest.dao;

import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import se331.lab.rest.entity.Organization;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Repository
@Profile("manual")
public class OrganizationDaoImpl implements OrganizationDao {
    List<Organization> organizationList;

    @PostConstruct
    public void init() {
        organizationList = new ArrayList<>();
        organizationList.add(Organization.builder()
                .id(123L)
                .category("animal welfare")
                .title("Cat Adoption Day")
                .description("Find your new feline friend at this event.")
                .location("Meow Town")
                .organizer("Kat Laydee")
                .build());
        organizationList.add(Organization.builder()
                .id(456L)
                .category("food")
                .title("Community Gardening")
                .description("Join us as we tend to the community edible plants.")
                .location("Flora City")
                .organizer("Fern Pollin")
                .build());
        organizationList.add(Organization.builder()
                .id(789L)
                .category("sustainability")
                .title("Beach Cleanup")
                .description("Help pick up trash along the shore.")
                .location("Playa Del Carmen")
                .organizer("Carey Wales")
                .build());
        organizationList.add(Organization.builder()
                .id(1001L)
                .category("animal welfare")
                .title("Dog Adoption Day")
                .description("Find your new canine friend at this event.")
                .location("Woof Town")
                .organizer("Dawg Dahd")
                .build());
        organizationList.add(Organization.builder()
                .id(1002L)
                .category("food")
                .title("Canned Food Drive")
                .description("Bring your canned food to donate to those in need.")
                .location("Tin City")
                .organizer("Kahn Opiner")
                .build());
        organizationList.add(Organization.builder()
                .id(1003L)
                .category("sustainability")
                .title("Highway Cleanup")
                .description("Help pick up trash along the highway.")
                .location("Highway 50")
                .organizer("Brody Kill")
                .build());
    }

    @Override
    public Integer getOrganizationSize() {
        return organizationList.size();
    }

    @Override
    public Page<Organization> getOrganizations(Integer pageSize, Integer page) {
        pageSize = pageSize == null ? organizationList.size() : pageSize;
        page = page == null ? 1 : page;
        int firstIndex = (page - 1) * pageSize;
        return new PageImpl<Organization>(organizationList.subList(firstIndex,firstIndex+pageSize), PageRequest.of(page,pageSize),organizationList.size());
    }

    @Override
    public Organization getOrganization(Long id) {
        return organizationList.stream().filter(event -> event.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public Organization save(Organization organization) {
        organization.setId(organizationList.get(organizationList.size()-1).getId()+1);
        organizationList.add(organization);
        return organization;
    }

}
