package com.platform.marketplace.Marketplace.Platform.service.event;

import com.platform.marketplace.Marketplace.Platform.model.Event;
import com.platform.marketplace.Marketplace.Platform.model.EventCategory;
import com.platform.marketplace.Marketplace.Platform.model.Location;
import com.platform.marketplace.Marketplace.Platform.model.Organisation;
import com.platform.marketplace.Marketplace.Platform.utility.consts.EntranceType;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class SpecificationEventFilter {
    public Specification<Event> filterEvents(String name,
                                             String organisationName,
                                             String address,
                                             String location,
                                             String entrance,
                                             String category,
                                             String keyword) {
        return Specification.where(withName(name))
                .and(withOrganisationName(organisationName))
                .and(withAddress(address))
                .and(withLocation(location))
                .and(withEntrance(entrance))
                .and(withCategory(category))
                .and(withKeyword(keyword));
    }

    private Specification<Event> withName(String name) {
        return (root, query, builder) -> {
            if (name == null) {
                return null;
            }
            return builder.and(
                    builder.like(root.get("name"), "%" + name + "%"),
                    builder.isTrue(root.get("isEnabled")));
        };
    }

    private Specification<Event> withOrganisationName(String organisationName) {
        return (root, query, builder) -> {
            if (organisationName == null) {
                return null;
            }
            Join<Event, Organisation> join = root.join("organisation", JoinType.INNER);
            return builder.and(
                    builder.like(join.get("organisationName"), "%" + organisationName + "%"),
                    builder.isTrue(root.get("isEnabled")));
        };
    }

    private Specification<Event> withAddress(String address) {
        return (root, query, builder) -> {
            if (address == null) {
                return null;
            }
            return builder.and(
                    builder.like(root.get("address"), "%" + address + "%"),
                    builder.isTrue(root.get("isEnabled")));
        };
    }

    private Specification<Event> withLocation(String location) {
        return (root, query, builder) -> {
            if (location == null) {
                return null;
            }
            Join<Event, Location> join = root.join("locations", JoinType.INNER);
            return builder.and(
                    builder.like(join.get("city"), "%" + location + "%"),
                    builder.isTrue(root.get("isEnabled")));
        };
    }

    private Specification<Event> withEntrance(String entrance) {
        return (root, query, builder) -> {
            if (entrance == null) {
                return null;
            }
            return builder.and(
                    builder.equal(root.get("entranceType"), EntranceType.valueOf(entrance)),
                    builder.isTrue(root.get("isEnabled")));
        };
    }

    private Specification<Event> withCategory(String category) {
        return (root, query, builder) -> {
            if (category == null) {
                return null;
            }
            Join<Event, EventCategory> join = root.join("eventCategories", JoinType.INNER);
            return builder.and(
                    builder.like(join.get("type"), "%" + category + "%"),
                    builder.isTrue(root.get("isEnabled")));
        };

    }

    private Specification<Event> withKeyword(String keyword) {
        return (root, query, builder) -> {
            if (keyword == null) {
                return null;
            }
            return builder.and(
                    builder.like(root.get("keyWords"), "%" + keyword + "%"),
                    builder.isTrue(root.get("isEnabled")));
        };
    }
}
