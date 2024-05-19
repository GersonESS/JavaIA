package cadpessoas;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PessoaDAO {
    private List<Pessoa> pessoas = new ArrayList<>();

    public void addPessoa(Pessoa pessoa) {
        pessoas.add(pessoa);
    }

    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    public Optional<Pessoa> getPessoaByCpf(String cpf) {
        return pessoas.stream().filter(p -> p.getCpf().equals(cpf)).findFirst();
    }

    public boolean updatePessoa(String cpf, Pessoa pessoaAtualizada) {
        Optional<Pessoa> pessoaOptional = getPessoaByCpf(cpf);
        if (pessoaOptional.isPresent()) {
            Pessoa pessoa = pessoaOptional.get();
            pessoa.setNome(pessoaAtualizada.getNome());
            pessoa.setSexo(pessoaAtualizada.getSexo());
            pessoa.setCpf(pessoaAtualizada.getCpf());
            return true;
        }
        return false;
    }

    public boolean deletePessoa(String cpf) {
        Optional<Pessoa> pessoaOptional = getPessoaByCpf(cpf);
        if (pessoaOptional.isPresent()) {
            pessoas.remove(pessoaOptional.get());
            return true;
        }
        return false;
    }
}