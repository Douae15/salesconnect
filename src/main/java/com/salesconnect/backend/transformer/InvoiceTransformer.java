package com.salesconnect.backend.transformer;

import com.salesconnect.backend.dto.*;
import com.salesconnect.backend.entity.*;

public class InvoiceTransformer extends Transformer<Invoice, InvoiceDTO>{
    @Override
    public Invoice toEntity(InvoiceDTO invoiceDTO) {
        if (invoiceDTO == null)
            return null;
        else {
            Transformer<Order, OrderDTO> orderTransformer = new OrderTransformer();
            Transformer<Payment, PaymentDTO> paymentsTransformer = new PaymentTransformer();
            Invoice invoice = new Invoice();
            invoice.setDocumentNumber(invoiceDTO.getDocumentNumber());
            invoice.setDocumentNumber(invoiceDTO.getDocumentNumber());
            invoice.setAmount(invoiceDTO.getAmount());
            invoice.setStatus(invoiceDTO.getStatus());
            invoice.setDueDate(invoiceDTO.getDueDate());
            invoice.setOrder(orderTransformer.toEntity(invoiceDTO.getOrderDTO()));
            invoice.setPayments(paymentsTransformer.toEntityList(invoiceDTO.getPaymentsDTO()));
            return invoice;
        }
    }

    @Override
    public InvoiceDTO toDTO(Invoice invoice) {
        if (invoice == null)
            return null;
        else {
            Transformer<Order, OrderDTO> orderTransformer = new OrderTransformer();
            Transformer<Payment, PaymentDTO> paymentsTransformer = new PaymentTransformer();
            InvoiceDTO invoiceDTO = new InvoiceDTO();
            invoiceDTO.setDocumentNumber(invoice.getDocumentNumber());
            invoiceDTO.setDocumentNumber(invoice.getDocumentNumber());
            invoiceDTO.setAmount(invoice.getAmount());
            invoiceDTO.setStatus(invoice.getStatus());
            invoiceDTO.setDueDate(invoice.getDueDate());
            invoiceDTO.setOrderDTO(orderTransformer.toDTO(invoice.getOrder()));
            invoiceDTO.setPaymentsDTO(paymentsTransformer.toDTOList(invoice.getPayments()));
            return invoiceDTO;
        }
    }
}
