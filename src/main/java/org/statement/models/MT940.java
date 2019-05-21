package org.statement.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@XmlRootElement(name ="record")
@XmlAccessorType(XmlAccessType.NONE)
public class MT940 {
    @XmlAttribute(name = "reference")
    private Integer transactionRef;

    @XmlElement(name = "accountNumber")
    private String accountNumber;

    @XmlElement(name = "description")
    private String description;

    @XmlElement(name = "startBalance")
    private Float startBalance;


    @XmlElement(name = "mutation")
    private Float mutation;

    @XmlElement(name = "endBalance")
    private Float endBalance;
}
