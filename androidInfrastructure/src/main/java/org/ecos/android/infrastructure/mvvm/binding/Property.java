package org.ecos.android.infrastructure.mvvm.binding;

public class Property {
    private static final String NO_DETAIL = "";
    private final int mPropertyId;
    private String mChangeDetail;

    private Property(int propertyId, String changeDetail) {
        mPropertyId = propertyId;
        mChangeDetail = changeDetail;
    }

    public static Property from(int propertyId) {
        return Property.from(propertyId,NO_DETAIL);
    }

    public static Property from(int propertyId, String changeDetail) {
        if(changeDetail == null)
            throw new IllegalArgumentException("Change detail can not be null");
        return new Property(propertyId,changeDetail);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Property property = (Property) o;

        return mPropertyId == property.mPropertyId && (mChangeDetail != null ? mChangeDetail.equals(property.mChangeDetail) : property.mChangeDetail == null);
    }

    @Override
    public int hashCode() {
        int result = mPropertyId;
        result = 31 * result + (mChangeDetail != null ? mChangeDetail.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Property{" +
            "mPropertyId=" + mPropertyId +
            ", mChangeDetail='" + mChangeDetail + '\'' +
            '}';
    }
}
