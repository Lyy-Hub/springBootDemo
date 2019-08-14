package com.lyy.service.copier;

/**
 * Created by liyueyang on 2019/6/3.
 */
public abstract class AbstractCopier<SRC, TAG> {
    abstract TAG copy(SRC src, TAG tag);
}
