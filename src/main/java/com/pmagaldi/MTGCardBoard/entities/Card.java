package com.pmagaldi.MTGCardBoard.entities;

import java.util.Arrays;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_card")
public class Card {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String lang;
    private String manaCost;
    private Integer cmc;
    private String[] colorIdentity;
    private boolean foil;
    private String setName;
    private String imageUrl;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((manaCost == null) ? 0 : manaCost.hashCode());
        result = prime * result + ((cmc == null) ? 0 : cmc.hashCode());
        result = prime * result + Arrays.hashCode(colorIdentity);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Card other = (Card) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (manaCost == null) {
            if (other.manaCost != null)
                return false;
        } else if (!manaCost.equals(other.manaCost))
            return false;
        if (cmc == null) {
            if (other.cmc != null)
                return false;
        } else if (!cmc.equals(other.cmc))
            return false;
        if (!Arrays.equals(colorIdentity, other.colorIdentity))
            return false;
        return true;
    }

}
