package com.company.fileservice.repository;

import com.company.fileservice.modul.Image;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Map;

@Component
@Repository
@RequiredArgsConstructor
public class ImageRepositoryImpl {
    private final EntityManager entityManager;

    public Page<Image> getImages(Map<String, String> params) {
        int size = 10, page = 0;
        if (params.containsKey("page")) {
            page = Integer.parseInt(params.get("page"));
        }
        if (params.containsKey("size")) {
            size = Integer.parseInt(params.get("size"));
        }
        String strQuery = "select i from Image i where 1=1 ";
        String countQuery = "select count(i.imageId) from Image i where 1=1 ";
        StringBuilder buildParam = builderParams(params);
        Query query = entityManager.createQuery(strQuery + buildParam);
        Query queryTwo = entityManager.createQuery(countQuery + buildParam);

        setParams(query, params);
        setParams(queryTwo, params);

        query.setFirstResult(size * page);
        query.setMaxResults(size);
        long totalElement = Long.parseLong(queryTwo.getSingleResult().toString());
        return new PageImpl<>(query.getResultList(), PageRequest.of(page, size), totalElement);
    }

    private void setParams(Query query, Map<String, String> params) {
        if (params.containsKey("imageId")) {
            query.setParameter("imageId", params.get("imageId"));
        }
        if (params.containsKey("path")) {
            query.setParameter("path", params.get("path"));
        }
        if (params.containsKey("type")) {
            query.setParameter("type", params.get("type"));
        }
        if (params.containsKey("size")) {
            query.setParameter("size", Integer.parseInt(params.get("size")));
        }
        if (params.containsKey("token")) {
            query.setParameter("token", params.get("token"));
        }
        if (params.containsKey("createdAt")) {
            query.setParameter("createdAt", LocalDateTime.parse(params.get("createdAt")));
        }
        if (params.containsKey("updatedAt")) {
            query.setParameter("updatedAt", LocalDateTime.parse(params.get("updatedAt")));
        }
        if (params.containsKey("deletedAt")) {
            query.setParameter("deletedAt", LocalDateTime.parse(params.get("deletedAt")));
        }
    }

    private StringBuilder builderParams(Map<String, String> params) {
        StringBuilder stringBuilder = new StringBuilder();
        if (params.containsKey("imageId")) {
            stringBuilder.append(" AND i.imageId = :imageId");
        }
        if (params.containsKey("path")) {
            stringBuilder.append(" AND i.path = :path");
        }
        if (params.containsKey("type")) {
            stringBuilder.append(" AND i.type = :type");
        }
        if (params.containsKey("size")) {
            stringBuilder.append(" AND i.size = :size");
        }
        if (params.containsKey("token")) {
            stringBuilder.append(" AND i.token = :token");
        }
        if (params.containsKey("createdAt")) {
            stringBuilder.append(" AND i.createdAt = :createdAt");
        }
        if (params.containsKey("updatedAt")) {
            stringBuilder.append(" AND i.updatedAt = :updatedAt");
        }
        if (params.containsKey("deletedAt")) {
            stringBuilder.append(" AND i.deletedAt = :deletedAt");
        }
        return stringBuilder;
    }
}