package com.example.springboot.repositories;

import com.example.springboot.models.DeployItemModel;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeployItemRepository extends JpaRepository<DeployItemModel, UUID> {



}