/*
 * This file is generated by jOOQ.
 */
package jooq.generated.tables.records;


import jooq.generated.tables.ItemsPayment;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record4;
import org.jooq.Row4;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class ItemsPaymentRecord extends UpdatableRecordImpl<ItemsPaymentRecord> implements Record4<Long, Long, String, Double> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.items_payment.id</code>.
     */
    public void setId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.items_payment.id</code>.
     */
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>public.items_payment.id_payment</code>.
     */
    public void setIdPayment(Long value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.items_payment.id_payment</code>.
     */
    public Long getIdPayment() {
        return (Long) get(1);
    }

    /**
     * Setter for <code>public.items_payment.name</code>.
     */
    public void setName(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.items_payment.name</code>.
     */
    public String getName() {
        return (String) get(2);
    }

    /**
     * Setter for <code>public.items_payment.cost</code>.
     */
    public void setCost(Double value) {
        set(3, value);
    }

    /**
     * Getter for <code>public.items_payment.cost</code>.
     */
    public Double getCost() {
        return (Double) get(3);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record4 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row4<Long, Long, String, Double> fieldsRow() {
        return (Row4) super.fieldsRow();
    }

    @Override
    public Row4<Long, Long, String, Double> valuesRow() {
        return (Row4) super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return ItemsPayment.ITEMS_PAYMENT.ID;
    }

    @Override
    public Field<Long> field2() {
        return ItemsPayment.ITEMS_PAYMENT.ID_PAYMENT;
    }

    @Override
    public Field<String> field3() {
        return ItemsPayment.ITEMS_PAYMENT.NAME;
    }

    @Override
    public Field<Double> field4() {
        return ItemsPayment.ITEMS_PAYMENT.COST;
    }

    @Override
    public Long component1() {
        return getId();
    }

    @Override
    public Long component2() {
        return getIdPayment();
    }

    @Override
    public String component3() {
        return getName();
    }

    @Override
    public Double component4() {
        return getCost();
    }

    @Override
    public Long value1() {
        return getId();
    }

    @Override
    public Long value2() {
        return getIdPayment();
    }

    @Override
    public String value3() {
        return getName();
    }

    @Override
    public Double value4() {
        return getCost();
    }

    @Override
    public ItemsPaymentRecord value1(Long value) {
        setId(value);
        return this;
    }

    @Override
    public ItemsPaymentRecord value2(Long value) {
        setIdPayment(value);
        return this;
    }

    @Override
    public ItemsPaymentRecord value3(String value) {
        setName(value);
        return this;
    }

    @Override
    public ItemsPaymentRecord value4(Double value) {
        setCost(value);
        return this;
    }

    @Override
    public ItemsPaymentRecord values(Long value1, Long value2, String value3, Double value4) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached ItemsPaymentRecord
     */
    public ItemsPaymentRecord() {
        super(ItemsPayment.ITEMS_PAYMENT);
    }

    /**
     * Create a detached, initialised ItemsPaymentRecord
     */
    public ItemsPaymentRecord(Long id, Long idPayment, String name, Double cost) {
        super(ItemsPayment.ITEMS_PAYMENT);

        setId(id);
        setIdPayment(idPayment);
        setName(name);
        setCost(cost);
    }
}