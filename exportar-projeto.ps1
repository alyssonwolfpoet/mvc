# Diretório raiz do projeto (onde o script está localizado)
$projeto = Split-Path -Parent $MyInvocation.MyCommand.Definition
$saida = Join-Path $projeto "projeto_exportado.txt"

# Extensões relevantes
$extensoes = @("*.java", "*.xml", "*.yml", "*.yaml", "*.properties", "*.html", "*.js", "*.css", "*.md")

# Pastas a ignorar (por nome)
$ignorar = @("target", ".git", ".idea", "node_modules", "build", "out", ".vscode", ".gradle")

# Verifica se o caminho está dentro de uma pasta ignorada
function EstaEmPastaIgnorada($caminho) {
    foreach ($ign in $ignorar) {
        if ($caminho -like "*\$ign\*") {
            return $true
        }
    }
    return $false
}

# Função para mostrar a árvore de diretórios
function Get-Tree {
    param (
        [string]$path,
        [int]$nivel = 0
    )
    Get-ChildItem -Path $path | ForEach-Object {
        if (-not ($ignorar -contains $_.Name)) {
            $indent = " " * ($nivel * 2)
            Add-Content -Path $saida -Value ("$indent|- " + $_.Name)
            if ($_.PSIsContainer) {
                Get-Tree -path $_.FullName -nivel ($nivel + 1)
            }
        }
    }
}

# Início
"==== ÁRVORE DO PROJETO ====" | Out-File -FilePath $saida -Encoding UTF8
Get-Tree -path $projeto

"`r`n==== CÓDIGOS FONTE ====`r`n" | Add-Content -Path $saida

# Coleta arquivos válidos, ignorando pastas desnecessárias
Get-ChildItem -Path $projeto -Recurse -Include $extensoes -File |
Where-Object {
    -not (EstaEmPastaIgnorada $_.FullName)
} | ForEach-Object {
    "`r`n==== INICIO: $($_.FullName) ====" | Add-Content -Path $saida
    Get-Content $_.FullName | Add-Content -Path $saida
    "`r`n==== FIM: $($_.FullName) ====" | Add-Content -Path $saida
}

Write-Host "✅ Arquivo gerado com sucesso: $saida"
