package com.salesconnect.backend.transformer;

import com.salesconnect.backend.dto.*;
import com.salesconnect.backend.entity.*;

public class OrderTransformer extends Transformer<Order, OrderDTO>{
    @Override
    public Order toEntity(OrderDTO orderDTO) {
        if (orderDTO == null)
            return null;
        else {
            Transformer<Company, CompanyDTO> companyTransformer = new CompanyTransformer();
            Transformer<Contract, ContractDTO> contractTransformer = new ContractTransformer();
            Transformer<Invoice, InvoiceDTO> invoicesTransformer = new InvoiceTransformer();
            Order order = new Order();
            order.setOrderNumber(orderDTO.getOrderNumber());
            order.setName(orderDTO.getName());
            order.setOrderDate(orderDTO.getOrderDate());
            order.setDeliveryDate(orderDTO.getDeliveryDate());
            order.setStatus(orderDTO.getStatus());
            order.setCompany(companyTransformer.toEntity(orderDTO.getCompanyDTO()));
            order.setContract(contractTransformer.toEntity(orderDTO.getContractDTO()));
            order.setInvoices(invoicesTransformer.toEntityList(orderDTO.getInvoicesDTO()));
            return order;
        }
    }

    @Override
    public OrderDTO toDTO(Order order) {
        if (order == null)
            return null;
        else {
            Transformer<Company, CompanyDTO> companyTransformer = new CompanyTransformer();
            Transformer<Contract, ContractDTO> contractTransformer = new ContractTransformer();
            Transformer<Invoice, InvoiceDTO> invoicesTransformer = new InvoiceTransformer();
            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setOrderNumber(order.getOrderNumber());
            orderDTO.setName(order.getName());
            orderDTO.setOrderDate(order.getOrderDate());
            orderDTO.setDeliveryDate(order.getDeliveryDate());
            orderDTO.setStatus(order.getStatus());
            orderDTO.setCompanyDTO(companyTransformer.toDTO(order.getCompany()));
            orderDTO.setContractDTO(contractTransformer.toDTO(order.getContract()));
            orderDTO.setInvoicesDTO(invoicesTransformer.toDTOList(order.getInvoices()));
            return orderDTO;
        }
    }
}
