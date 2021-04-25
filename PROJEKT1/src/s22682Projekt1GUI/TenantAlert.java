package s22682Projekt1GUI;

class TenantAlert{
    int id;
    Rent rent;
    Date ExpirationDate;
    TenantAlert(int id, Rent rent){
        this.id=id;
        this.rent=rent;
        ExpirationDate= rent.ExpirationDate.plusDays(rent.ExpirationDate, 30);
    }

    public String toString() {
        return "(Zadluzenie nr." + id +
                ": wynajem: " + rent +
                ", Data wygasniecia" + ExpirationDate +
                ')';
    }
}
