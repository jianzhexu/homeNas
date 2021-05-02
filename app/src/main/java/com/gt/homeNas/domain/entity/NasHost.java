package com.gt.homeNas.domain.entity;

import com.gt.homeNas.types.NasAddress;
import com.gt.homeNas.types.NasDisk;
import com.gt.homeNas.types.NasId;
import lombok.Data;

@Data
public class NasHost {

    private NasAddress nasAddress;

    private NasDisk nasDisk;

    private NasId nasId;

    public NasHost(NasAddress nasAddress, NasDisk nasDisk, NasId nasId) {
        this.nasAddress = nasAddress;
        this.nasDisk = nasDisk;
        this.nasId = nasId;
    }

}
