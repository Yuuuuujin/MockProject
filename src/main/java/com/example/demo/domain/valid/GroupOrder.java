package com.example.demo.domain.valid;

import javax.validation.GroupSequence;

@GroupSequence({ ValidGroup1.class, ValidGroup2.class, ValidGroup3.class })
public interface GroupOrder {

}
