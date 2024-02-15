package com.example.memento;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Javen
 * @date 2024/2/15
 *
 * 备忘录是一个很特殊的对象，只有原发器对它拥有控制的权力，负责人只负责管理，而其他类无法访问到备忘录，因此我们需要对备忘录进行封装。
 * 为了实现对备忘录对象的封装，需要对备忘录的调用进行控制，
 *      对于原发器而言，它可以调用备忘录的所有信息，允许原发器访问返回到先前状态所需的所有数据；
 *      对于负责人而言，只负责备忘录的保存并将备忘录传递给其他对象；
 *      对于其他对象而言，只需要从负责人处取出备忘录对象并将原发器对象的状态恢复，而无须关心备忘录的保存细节。
 * 理想的情况是只允许生成该备忘录的那个原发器访问备忘录的内部状态。
 *
 * 在Java语言中可以将原发器类和备忘录类放在一个包中，让它们之间满足默认的包内可见性，
 * 也可以将备忘录类作为原发器类的内部类，使得只有原发器才可以访问备忘录中的数据，其他对象都无法使用备忘录中的数据。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChessmanMemento {

    private String label;
    private int x;
    private int y;
}
