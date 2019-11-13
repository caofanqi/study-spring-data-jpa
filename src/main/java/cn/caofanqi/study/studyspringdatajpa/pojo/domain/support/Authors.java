package cn.caofanqi.study.studyspringdatajpa.pojo.domain.support;

import cn.caofanqi.study.studyspringdatajpa.pojo.domain.Author;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.util.Streamable;

import java.util.Iterator;

/**
 * 自定义Streamable类
 * @author caofanqi
 */
@Data
@NoArgsConstructor
public class Authors implements Streamable<Author> {

    private Streamable<Author> streamable;

    public Authors(Streamable<Author> streamable){
        this.streamable = streamable;
    }

    public String getXXX(){
        return "提供方法";
    }

    @Override
    public Iterator<Author> iterator() {
        return streamable.iterator();
    }
}
