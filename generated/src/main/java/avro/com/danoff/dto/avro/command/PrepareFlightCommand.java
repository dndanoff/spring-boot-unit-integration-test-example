/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package com.danoff.dto.avro.command;

import org.apache.avro.specific.SpecificData;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class PrepareFlightCommand extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 8359130220697977793L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"PrepareFlightCommand\",\"namespace\":\"com.danoff.dto.avro.command\",\"fields\":[{\"name\":\"id\",\"type\":\"long\"},{\"name\":\"departureAirport\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"arrivalAirport\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<PrepareFlightCommand> ENCODER =
      new BinaryMessageEncoder<PrepareFlightCommand>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<PrepareFlightCommand> DECODER =
      new BinaryMessageDecoder<PrepareFlightCommand>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   */
  public static BinaryMessageDecoder<PrepareFlightCommand> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   */
  public static BinaryMessageDecoder<PrepareFlightCommand> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<PrepareFlightCommand>(MODEL$, SCHEMA$, resolver);
  }

  /** Serializes this PrepareFlightCommand to a ByteBuffer. */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /** Deserializes a PrepareFlightCommand from a ByteBuffer. */
  public static PrepareFlightCommand fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  @Deprecated public long id;
  @Deprecated public java.lang.String departureAirport;
  @Deprecated public java.lang.String arrivalAirport;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public PrepareFlightCommand() {}

  /**
   * All-args constructor.
   * @param id The new value for id
   * @param departureAirport The new value for departureAirport
   * @param arrivalAirport The new value for arrivalAirport
   */
  public PrepareFlightCommand(java.lang.Long id, java.lang.String departureAirport, java.lang.String arrivalAirport) {
    this.id = id;
    this.departureAirport = departureAirport;
    this.arrivalAirport = arrivalAirport;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return id;
    case 1: return departureAirport;
    case 2: return arrivalAirport;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: id = (java.lang.Long)value$; break;
    case 1: departureAirport = (java.lang.String)value$; break;
    case 2: arrivalAirport = (java.lang.String)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'id' field.
   * @return The value of the 'id' field.
   */
  public java.lang.Long getId() {
    return id;
  }

  /**
   * Sets the value of the 'id' field.
   * @param value the value to set.
   */
  public void setId(java.lang.Long value) {
    this.id = value;
  }

  /**
   * Gets the value of the 'departureAirport' field.
   * @return The value of the 'departureAirport' field.
   */
  public java.lang.String getDepartureAirport() {
    return departureAirport;
  }

  /**
   * Sets the value of the 'departureAirport' field.
   * @param value the value to set.
   */
  public void setDepartureAirport(java.lang.String value) {
    this.departureAirport = value;
  }

  /**
   * Gets the value of the 'arrivalAirport' field.
   * @return The value of the 'arrivalAirport' field.
   */
  public java.lang.String getArrivalAirport() {
    return arrivalAirport;
  }

  /**
   * Sets the value of the 'arrivalAirport' field.
   * @param value the value to set.
   */
  public void setArrivalAirport(java.lang.String value) {
    this.arrivalAirport = value;
  }

  /**
   * Creates a new PrepareFlightCommand RecordBuilder.
   * @return A new PrepareFlightCommand RecordBuilder
   */
  public static com.danoff.dto.avro.command.PrepareFlightCommand.Builder newBuilder() {
    return new com.danoff.dto.avro.command.PrepareFlightCommand.Builder();
  }

  /**
   * Creates a new PrepareFlightCommand RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new PrepareFlightCommand RecordBuilder
   */
  public static com.danoff.dto.avro.command.PrepareFlightCommand.Builder newBuilder(com.danoff.dto.avro.command.PrepareFlightCommand.Builder other) {
    return new com.danoff.dto.avro.command.PrepareFlightCommand.Builder(other);
  }

  /**
   * Creates a new PrepareFlightCommand RecordBuilder by copying an existing PrepareFlightCommand instance.
   * @param other The existing instance to copy.
   * @return A new PrepareFlightCommand RecordBuilder
   */
  public static com.danoff.dto.avro.command.PrepareFlightCommand.Builder newBuilder(com.danoff.dto.avro.command.PrepareFlightCommand other) {
    return new com.danoff.dto.avro.command.PrepareFlightCommand.Builder(other);
  }

  /**
   * RecordBuilder for PrepareFlightCommand instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<PrepareFlightCommand>
    implements org.apache.avro.data.RecordBuilder<PrepareFlightCommand> {

    private long id;
    private java.lang.String departureAirport;
    private java.lang.String arrivalAirport;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(com.danoff.dto.avro.command.PrepareFlightCommand.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.id)) {
        this.id = data().deepCopy(fields()[0].schema(), other.id);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.departureAirport)) {
        this.departureAirport = data().deepCopy(fields()[1].schema(), other.departureAirport);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.arrivalAirport)) {
        this.arrivalAirport = data().deepCopy(fields()[2].schema(), other.arrivalAirport);
        fieldSetFlags()[2] = true;
      }
    }

    /**
     * Creates a Builder by copying an existing PrepareFlightCommand instance
     * @param other The existing instance to copy.
     */
    private Builder(com.danoff.dto.avro.command.PrepareFlightCommand other) {
            super(SCHEMA$);
      if (isValidValue(fields()[0], other.id)) {
        this.id = data().deepCopy(fields()[0].schema(), other.id);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.departureAirport)) {
        this.departureAirport = data().deepCopy(fields()[1].schema(), other.departureAirport);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.arrivalAirport)) {
        this.arrivalAirport = data().deepCopy(fields()[2].schema(), other.arrivalAirport);
        fieldSetFlags()[2] = true;
      }
    }

    /**
      * Gets the value of the 'id' field.
      * @return The value.
      */
    public java.lang.Long getId() {
      return id;
    }

    /**
      * Sets the value of the 'id' field.
      * @param value The value of 'id'.
      * @return This builder.
      */
    public com.danoff.dto.avro.command.PrepareFlightCommand.Builder setId(long value) {
      validate(fields()[0], value);
      this.id = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'id' field has been set.
      * @return True if the 'id' field has been set, false otherwise.
      */
    public boolean hasId() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'id' field.
      * @return This builder.
      */
    public com.danoff.dto.avro.command.PrepareFlightCommand.Builder clearId() {
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'departureAirport' field.
      * @return The value.
      */
    public java.lang.String getDepartureAirport() {
      return departureAirport;
    }

    /**
      * Sets the value of the 'departureAirport' field.
      * @param value The value of 'departureAirport'.
      * @return This builder.
      */
    public com.danoff.dto.avro.command.PrepareFlightCommand.Builder setDepartureAirport(java.lang.String value) {
      validate(fields()[1], value);
      this.departureAirport = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'departureAirport' field has been set.
      * @return True if the 'departureAirport' field has been set, false otherwise.
      */
    public boolean hasDepartureAirport() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'departureAirport' field.
      * @return This builder.
      */
    public com.danoff.dto.avro.command.PrepareFlightCommand.Builder clearDepartureAirport() {
      departureAirport = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'arrivalAirport' field.
      * @return The value.
      */
    public java.lang.String getArrivalAirport() {
      return arrivalAirport;
    }

    /**
      * Sets the value of the 'arrivalAirport' field.
      * @param value The value of 'arrivalAirport'.
      * @return This builder.
      */
    public com.danoff.dto.avro.command.PrepareFlightCommand.Builder setArrivalAirport(java.lang.String value) {
      validate(fields()[2], value);
      this.arrivalAirport = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'arrivalAirport' field has been set.
      * @return True if the 'arrivalAirport' field has been set, false otherwise.
      */
    public boolean hasArrivalAirport() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'arrivalAirport' field.
      * @return This builder.
      */
    public com.danoff.dto.avro.command.PrepareFlightCommand.Builder clearArrivalAirport() {
      arrivalAirport = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public PrepareFlightCommand build() {
      try {
        PrepareFlightCommand record = new PrepareFlightCommand();
        record.id = fieldSetFlags()[0] ? this.id : (java.lang.Long) defaultValue(fields()[0]);
        record.departureAirport = fieldSetFlags()[1] ? this.departureAirport : (java.lang.String) defaultValue(fields()[1]);
        record.arrivalAirport = fieldSetFlags()[2] ? this.arrivalAirport : (java.lang.String) defaultValue(fields()[2]);
        return record;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<PrepareFlightCommand>
    WRITER$ = (org.apache.avro.io.DatumWriter<PrepareFlightCommand>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<PrepareFlightCommand>
    READER$ = (org.apache.avro.io.DatumReader<PrepareFlightCommand>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}
