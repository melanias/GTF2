package br.com.sti.gtf;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Specializes;

import br.com.caelum.vraptor.observer.upload.DefaultMultipartConfig;

/**
 *
 * @author Phelipe Melanias
 */
@Specializes
@ApplicationScoped
public class CustomMultipartConfig extends DefaultMultipartConfig{

    @Override
    public long getSizeLimit() {
        return 25 * 1024 * 1024;
    }

    @Override
    public long getFileSizeLimit() {
        return 25 * 1024 * 1024;
    }
}