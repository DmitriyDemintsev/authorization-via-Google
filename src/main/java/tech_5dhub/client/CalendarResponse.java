package tech_5dhub.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech_5dhub.client.dto.CalenderItems;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CalendarResponse {

    private List<CalenderItems> items;
}
